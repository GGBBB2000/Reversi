import java.io.IOException;
public class Main {

	public static void main(String[] args)throws IOException {
		Othello othello = new Othello();
		Othello.diff();
		for(;;){
			//Othello.write();
			System.out.println("初期配置");
			Othello.draw();
			Othello.search();
			//Othello.write();
			System.out.println("置ける場所を検索");
			Othello.draw();
			Othello.count();
			//Othello.reverse(maxi,maxj);
			//Othello.write();
			System.out.println("判断後");
			Othello.draw();
			if (Othello.human()==-1){
				System.out.println("end");
				break;
			}
			System.out.println("人間がおいた場所");
			Othello.draw();
		}
	}
}
