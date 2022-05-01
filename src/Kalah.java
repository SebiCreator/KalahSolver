/**
 * Hauptprogramm für KalahMuster.
 * @since 29.3.2021
 * @author oliverbittel
 */
public class Kalah {
	
	private static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_RED = "\u001B[31m";

	/**
	 *
	 * @param args wird nicht verwendet.
	 */
	public static void main(String[] args) {
		//testExample();
		//testHHGame();
		//test1();
		//play();
		testPerformance(18);
	}
	
	/**
	 * Beispiel von https://de.wikipedia.org/wiki/Kalaha
	 */
	public static void testExample() { 
		KalahBoard kalahBd = new KalahBoard(new int[]{5,3,2,1,2,0,0,4,3,0,1,2,2,0}, 'B');
		kalahBd.print();
		
		System.out.println("B spielt Mulde 11");
		kalahBd.move(11);
		kalahBd.print();
		
		System.out.println("B darf nochmals ziehen und spielt Mulde 7");
		kalahBd.move(7);
		kalahBd.print();
	}
	
	/**
	 * Mensch gegen Mensch
	 */
	public static void testHHGame() {
		KalahBoard kalahBd = new KalahBoard();
		kalahBd.print();

		while (!kalahBd.isFinished()) {
			int action = kalahBd.readAction();
			kalahBd.move(action);
			kalahBd.print();
		}

		System.out.println("\n" + ANSI_BLUE + "GAME OVER");
	}

	public static void play(){
		KalahBoard b = new KalahBoard();
		b.print();
		while(!b.isFinished()){
			if(b.getCurPlayer() == 'A'){
				int bestMove = KalahBoard.getBestMoveAB(b,8);
				b.move(bestMove);
				System.out.println("\n" + ANSI_RED + ">>> Player A (Computer) spielt Mulde " + bestMove + " <<<<\n" + ANSI_BLUE);
				b.print();
			} else{
				int action = b.readAction();
				b.move(action);
				b.print();
			}
		}
	}

	public static void testPerformance(int LIMIT){
		KalahBoard b = new KalahBoard();
		KalahBoard b2 = new KalahBoard(new int[]{13,3,2,1,2,0,5,4,3,0,1,2,2,0}, 'A');

		long startTime = System.nanoTime();
		//KalahBoard.miniMax(b,LIMIT,true);
		long stopTime = System.nanoTime();
		double expTime = (double) (stopTime - startTime) / 1.0e09;
		System.out.printf("Min/Max Aufrufe MiniMax bei einem Limit von %d ==> %d (%.5f s)\n",LIMIT,KalahBoard.COUNTER,expTime);


		startTime = System.nanoTime();
		KalahBoard.miniMaxAB(b,LIMIT,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
		stopTime = System.nanoTime();
		expTime = (double) (stopTime - startTime) / 1.0e09;
		System.out.printf("Min/Max Aufrufe AlphaBeta bei einem Limit von %d ==> %d (%.5f s)\n",LIMIT,KalahBoard.ABCOUNTER,expTime);

		startTime = System.nanoTime();
		KalahBoard.optMiniMax(b,LIMIT,true,Integer.MIN_VALUE,Integer.MAX_VALUE);
		stopTime = System.nanoTime();
		expTime = (double) (stopTime-startTime) / 1.0e09;
		System.out.printf("Min/Max Aufrufe AlphaBetaOptimiert bei einem Limit von %d ==> %d (%.5f s)\n",LIMIT,KalahBoard.OPTCOUNTER,expTime);


	}


	public static void test1(){
		KalahBoard b = new KalahBoard(new int[]{13,3,2,1,2,0,5,4,3,0,1,2,2,0}, 'A');
		KalahBoard b3 = new KalahBoard(new int[]{2,13,2,1,2,0,5,4,3,0,1,2,2,0}, 'B');
		KalahBoard b2 = new KalahBoard();
		System.out.println(KalahBoard.getBestMoveAB(b2,10));
	}
}
