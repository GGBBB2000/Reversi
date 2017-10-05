public class Main {

	public static void main(String[] args) {
		try{
			/*
			既存のバグ一覧
			：
			：
			不完全な仕様
			：人間は打てないところに打てる
			：
			*/
			//Othello othello = new Othello();
			Othello.diff();
			for(;;){
				//Othello.write();
				System.out.println("初期配置");
				Othello.draw();
				Othello.search();
				//Othello.write();
				if (false){
					System.out.println("置ける場所を検索");
					Othello.draw();
				}
				if (!Othello.count()){
					System.out.println("ゲームを終了します");
					break;
				}

				//Othello.reverse(maxi,maxj);
				//Othello.write();
				System.out.println("判断後");
				Othello.draw();
				if (!Othello.human()){
					System.out.println("end");
					break;
				}

				System.out.println("人間がおいた場所");
			}
		}catch(Exception e){
			System.out.println("Error");
		}
	}
}
