package Logic;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Logic.Algoritmos.Cruces.Cruce;
import Logic.Algoritmos.Individuos.Individuo;
import Logic.Algoritmos.Individuos.IndividuoComparator;
import Logic.Algoritmos.Mutaciones.Mutacion;
import Logic.Algoritmos.Selecciones.Seleccion;

public class AlgoritmoEvolutivo {

	public static double INF = 10E15;
	
	private Cruce cruce;
	private Seleccion seleccion;
	private Mutacion mutacion;
	
	
	ArrayList<Individuo> poblacion;
	ArrayList<double[]> evaluation;
	Individuo mejor, example;
	
	boolean maximizacion;
	int Npoblacion;
	int generaciones;
	double pCruce;
	double pMut;
	double pElitismo;
	
	double precision;
	int contReinicializacion;
	
	ArrayList<Observer> obs;
	
	public AlgoritmoEvolutivo() {
		obs = new ArrayList<Observer>();
		this.evaluation = new ArrayList<double[]>();
		this.poblacion = new ArrayList<Individuo>();
	}
	
	
	
	public void InitAlgoritmoEvolutivo(int Npoblacion, int generaciones, double pCruce, double pMut, double pElitismo,
								Seleccion sel, Cruce c, Mutacion mut, Individuo indv, double precision, boolean maximizacion) {
		
		this.precision = precision;
		this.maximizacion = maximizacion;
		
		this.generaciones = generaciones;
		this.Npoblacion = Npoblacion;
		
		this.cruce = c;
		this.mutacion = mut;
		this.seleccion = sel;
		this.example = indv;
		
		this.pCruce = pCruce;
		this.pMut = pMut;
		this.pElitismo = pElitismo;
		
		this.evaluation.clear();
		
		this.evaluation.add(new double[generaciones]);  //m�ximo
		this.evaluation.add(new double[generaciones]);  //m�nimo
		this.evaluation.add(new double[generaciones]);  //mejor
		this.evaluation.add(new double[generaciones]);  //media
		this.evaluation.add(new double[generaciones]);  //Presion Selectiva

		
		initPoblacion(example);
	
	}
	
	public void addObserver(Observer o) {
		obs.add(o);
	}
	
	public void initPoblacion(Individuo indv) {
		Individuo clon;
		
		this.poblacion.clear();
		
		for (int i = 0; i < Npoblacion; i++) {
			clon = indv.duplicate();
			clon.init();
			this.poblacion.add(clon);
		}
		
		this.mejor = poblacion.get(0).duplicate(); 
	}
	
	public void run() {
		int pElite;
		ArrayList<Individuo> elite = null;
		
		boolean ok = true;
		this.contReinicializacion = 0;
		
		for (int i = 0; i < generaciones; i++) {
			
			pElite = (int) Math.floor(pElitismo * Npoblacion);
			
			if (pElite > 0) {
				elite = this.seleccionElitistas(pElite);
			}
			
			poblacion = seleccion.doSeleccion(poblacion);
			
			poblacion = cruce.doCruce(poblacion, pCruce);
			
			poblacion = mutacion.doMutacion(poblacion, pMut);
			
			if (pElite > 0) {
				poblacion = this.ReintegraElite(elite);
			}
			
			this.evaluatePoblacion(i);
			if (i % 20 == 0) {
				for (Observer o: obs) {
					o.onGeneration(evaluation, i, mejor);
				}
			}
		}
		for (Observer o: obs) {
			o.onFinished(evaluation, this.generaciones, mejor);
		}
	}
	
	public void evaluatePoblacion(int generacion){
		
		double maximo, minimo, media, aux, presionSelectiva;
		
		double[] fitness = new double[this.Npoblacion];
		
		maximo = -1 * INF;
		minimo = INF;
		media = 0;
		
		for (int i = 0; i < Npoblacion; i++) {
			aux = poblacion.get(i).getFitness();
			
			fitness[i] = aux;
			
			if (!maximizacion && poblacion.get(i).getFitness() < mejor.getFitness()) {
				mejor = poblacion.get(i).duplicate();
			}
			if (maximizacion && poblacion.get(i).getFitness() > mejor.getFitness()) {
				mejor = poblacion.get(i).duplicate();
			}
			
			if (aux > maximo) {
				maximo = aux;
			}
				
			if (aux < minimo) {
				minimo = aux;
			}
			media += aux;
		}
			
		media = round(media / Npoblacion);
		presionSelectiva = calculaPresionSelectiva(fitness, minimo, maximo);
		
		if (presionSelectiva == 1.0) this.contReinicializacion++;
		else this.contReinicializacion = 0; 
		if (this.contReinicializacion == 10) {
			reinicializaPoblacion(0.2);
		}
		
		this.evaluation.get(0)[generacion] = maximo;
		this.evaluation.get(1)[generacion] = minimo;
		this.evaluation.get(2)[generacion] = mejor.getFitness();
		this.evaluation.get(3)[generacion] = media;
		this.evaluation.get(4)[generacion] = presionSelectiva;
		
		
	}
	
	public void reinicializaPoblacion(double pInit) {
		for (int i = 0; i < poblacion.size();i++) {
			if ( Math.random() <= pInit) {
				poblacion.get(i).init();
			}
		}
	}
	
	public double calculaPresionSelectiva(double[] fitness, double minimo, double maximo) {
		double mediaDesp, desp, mejor;
		
		if (maximizacion) {desp = -Math.abs(minimo + 1); mejor = -1*INF;}
		else {desp = -Math.abs(maximo + 1); mejor = INF;}

		mediaDesp = 0;
		for ( int i = 0; i < Npoblacion; i++) {
			fitness[i] += desp;
			mediaDesp += fitness[i];
			if (maximizacion) mejor = Math.max(mejor, fitness[i]);
			else mejor = Math.min(mejor, fitness[i]);
		}
		
		mediaDesp /= Npoblacion;
		
		return mejor / mediaDesp;
	}
	
	public ArrayList<Individuo> seleccionElitistas(int pElite) {
		
		ArrayList<Individuo> elite = new ArrayList<Individuo>();
		PriorityQueue<Individuo> q = new PriorityQueue<Individuo>(Npoblacion, new IndividuoComparator(this.maximizacion));
		
		for (Individuo indv : poblacion) {
			q.add(indv);
		}
		for (int i = 0; i < pElite; i++) {
			elite.add((q.poll()).duplicate());
		}
		return elite;
	}
	
	public ArrayList<Individuo> ReintegraElite(ArrayList<Individuo> elite) {
		double pIndv;
		int punto;
		for (int i = 0; i < elite.size(); i++) {
			pIndv = 1.0 / poblacion.size();
			punto = (int) Math.floor(Math.random()/pIndv);
			poblacion.remove(punto);
		}
		
		for (Individuo indv: elite) {
			poblacion.add(indv);
		}
		
		return poblacion; 
		
	}

	public void setAlgorithm(String function, String sel, String mut, String cruce) {
		for (Observer o: obs) {
			o.onAlgorithmChanged(function,sel, mut, cruce);
		}
	}
	
	private double round(double n) {
		double ratio = 1.0 / this.precision;
		return Math.round( (n*ratio) / ratio);
	}
	
	public void onException(String msg) {
		for (Observer o: obs) {
			o.onException(msg);
		}
	}
	
}
