import java.io.IOException;
import java.util.Scanner;
public class Othello {
	static int rock [][] = new int [30][30];
	static String koma[][] = new String [30][30];
	static int cn[][]=new int [30][30];
	static int max = 0;
	static int debag = 0;

	static void diff(){
		for (int j = 11;j<19;j++){
			for (int i = 11;i<19;i++){
				rock[i][j] = 1;
				rock[10+4][10+4] = 1;//１：黒
				rock[10+5][10+4] = 2;//２：白
				rock[10+4][10+5] = 2;//-1：黒がおける
				rock[10+5][10+5] = 1;//-2：白がおける
				rock[10+5][10+3] = 0;
			}
		}
	}

	static void write(){
		//8*8ます
		//ただしx,yともに0は欄外
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		for(int j = 11;j<19;j++){//j=y座標
			for (int i = 11;i<19;i++){//i=x座標
				System.out.print(rock[i][j]+" ");
				if (rock[i][j]>=0){
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}

	static void search() {
		//黒がおけるところ探索
		for(int j = 11;j<19;j++){
			for (int i = 11;i<19;i++){
				if(rock[i][j]==1){
					//上
					if (rock[i][j-1]==2){//黒かつ真上白
						for (int k=2;k>=2;k++){
							if (rock[i][j-k]==1){
								k=0;
							}
							if (rock[i][j-k]<=0){//空きマス
								rock[i][j-k]=-1;

								k=0;//直上のfor文からぬける
							}
						}
					}
					//右
					if (rock[i+1][j]==2){//黒かつ右白
						for (int k=2;k>=2;k++){
							if (rock[i+k][j]==1){
								k=0;
							}
							if (rock[i+k][j]<=0){//空きマス
								rock[i+k][j]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
					//左
					if (rock[i-1][j]==2){//黒かつ左白
						for (int k=2;k>=2;k++){
							if (rock[i-k][j]==1){
								k=0;
							}
							if (rock[i-k][j]<=0){//空きマス
								rock[i-k][j]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
					//下
					if (rock[i][j+1]==2){//黒かつ左白
						for (int k=2;k>=2;k++){
							if (rock[i][j+k]==1){
								k=0;
							}
							if (rock[i][j+k]<=0){//空きマス
								rock[i][j+k]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
					//右下
					if (rock[i+1][j+1]==2){//黒かつ左白
						for (int k=2;k>=2;k++){
							if (rock[i+k][j+k]==1){
								k=0;
							}
							if (rock[i+k][j+k]<=0){//空きマス
								rock[i+k][j+k]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
					//右上
					if (rock[i+1][j-1]==2){//黒かつ左白
						for (int k=2;k>=2;k++){
							if (rock[i+k][j-k]==1){
								k=0;
							}
							if (rock[i+k][j-k]<=0){//空きマス
								rock[i+k][j-k]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
					//左下
					if (rock[i-1][j+1]==2){//黒かつ左白
						for (int k=2;k>=2;k++){
							if (rock[i-k][j+k]==1){
								k=0;
							}
							if (rock[i-k][j+k]<=0){//空きマス
								rock[i-k][j+k]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
					//左上
					if (rock[i-1][j-1]==2){//黒かつ左白
						for (int k=2;k>=2;k++){
							if (rock[i-k][j-k]==1){
								k=0;
							}
							if (rock[i-k][j-k]<=0){//空きマス
								rock[i-k][j-k]=-1;
								k=0;//直上のfor文からぬける

							}
						}
					}
				}
			}
		}
	}
	static void count(){
		int judge = 0;
		int maxi = 0;
		int maxj = 0;
		for (int t = 11;t<19;t++){
			for (int s = 11;s<19;s++){
				if(rock[s][t] <= 0){
					judge = 1;
				}
			}
		}
		if (judge==0){
			System.out.println("コンピュータはパスしました");
		}
		judge=0;
		for(int j = 11;j<19;j++){
			for (int i = 11;i<19;i++){//こっからくっそ無駄な処理してて恥ずかしくないの。。。？
				//上
				int h = 0;
				if (rock[i][j]==-1){
					//上
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock[i][j-k]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i-10)+","+(j-k-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i][j-k]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i][j-k]==1&&h==0){
							break;
						}
					}
					//右
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i+k][j]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock [i+k][j]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i+k-10)+","+(j-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i+k][j]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;

						}
						if (rock[i+k][j]==1&&h==0){
							break;
						}
					}
					//下
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock[i][j+k]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i-10)+","+(j+k-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i][j+k]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i][j+k]==1&&h==0){
							break;
						}
					}
					//左
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i-k][j]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock [i-k][j]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i-k-10)+","+(j-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i-k][j]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i-k][j]==1&&h==0){
							break;
						}


					}
					//右上
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i+k][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock [i+k][j-k]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i+k-10)+","+(j-k-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i+k][j-k]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i+k][j-k]==1&&h==0){
							break;
						}
					}
					//右下
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i+k][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock [i+k][j+k]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i+k-10)+","+(j+k-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i+k][j+k]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i+k][j+k]==1&&h==0){
							break;
						}
					}
					//左下
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i-k][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock[i-k][j+k]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i-k-10)+","+(j+k-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i-k][j+k]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i-k][j+k]==1&&h==0){
							break;
						}
					}
					//左上
					for (int k=1;k>=0;k++){//このfor文は全方向でいっこでいいよね
						if (rock[i-k][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
							h = 0;
							break;//直上のfor文からぬける
						}
						if (rock [i-k][j-k]==2){
							h = 1;
							if (debag == 1){
								System.out.println("count(x,y)= "+(i-k-10)+","+(j-k-10));
								System.out.println("k = "+k);
							}
						}
						if (rock[i-k][j-k]==1&&h==1){
							cn[i][j]+=k-1;//ひっくりかえる数を加算
							h = 0;
							if (debag == 1){
								System.out.println("k = "+k);
							}
							break;
						}
						if (rock[i-k][j-k]==1&&h==0){
							break;
						}
					}
				}

				if (rock[i][j]==-1){//その座標においたとき裏返る個数＝count
					if (debag == 1){
						System.out.println("x,y= "+(i-10)+","+(j-10)+" count= "+(cn[i][j]));
					}
					if (cn[i][j]>=max){
						maxi=i;maxj=j;max=cn[i][j];
					}
					cn[i][j]=0;
				}
			}
		}
		max = 0;
		System.out.println("change(x,y)="+(maxi-10)+","+(maxj-10));
		rock[maxi][maxj] = 1;
		final int i = maxi;
		final int j = maxj;
		int u=0;
		//右
		for (int k=0;k>=0;k++){
			if (rock[i+k][j]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i+k][j]==2){
				u=1;
			}
			if (u==1&&rock[i+k][j]==1){
				for(int s=0;s<k;s++){
					rock[i+s][j]=1;
				}
			}
		}
		//左
		for (int k=0;k>=0;k++){
			if (rock[i-k][j]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i-k][j]==2){
				u=1;
			}
			if (u==1&&rock[i-k][j]==1){
				for(int s=0;s<k;s++){
					rock[i-s][j]=1;
				}
			}
		}
		//上
		for (int k=0;k>=0;k++){
			if (rock[i][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i][j-k]==2){
				u=1;
			}
			if (u==1&&rock[i][j-k]==1){
				for(int s=0;s<k;s++){
					rock[i][j-s]=1;
				}
			}
		}
		//下
		for (int k=0;k>=0;k++){
			if (rock[i][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i][j+k]==2){
				u=1;
			}
			if (u==1&&rock[i][j+k]==1){
				for(int s=0;s<k;s++){
					rock[i][j+s]=1;
				}
			}
		}
		//右上
		for (int k=0;k>=0;k++){
			if (rock[i+k][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i+k][j-k]==2){
				u=1;
			}
			if (u==1&&rock[i+k][j-k]==1){
				for(int s=0;s<k;s++){
					rock[i+s][j-s]=1;
				}
			}
		}
		//右下
		for (int k=0;k>=0;k++){
			if (rock[i+k][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i+k][j+k]==2){
				u=1;
			}
			if (u==1&&rock[i+k][j+k]==1){
				for(int s=0;s<k;s++){
					rock[i+s][j+s]=1;
				}
			}
		}
		//左下
		for (int k=0;k>=0;k++){
			if (rock[i-k][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i-k][j+k]==2){
				u=1;
			}
			if (u==1&&rock[i-k][j+k]==1){
				for(int s=0;s<k;s++){
					rock[i-s][j+s]=1;
				}
			}
		}
		//左上
		for (int k=0;k>=0;k++){
			if (rock[i-k][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i-k][j-k]==2){
				u=1;
			}
			if (u==1&&rock[i-k][j-k]==1){
				for(int s=0;s<k;s++){
					rock[i-s][j-s]=1;
				}
			}
		}
		for(int s=11;s<19;s++){
			for(int t=11;t<19;t++){
				if (rock[t][s]==-1){
					rock[t][s]=0;
				}
			}
		}
	}

	static void draw(){
		//String[][] d = new String[30][30];
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		//System.out.print("  1　｜　2　｜　3　｜　4　｜　5　｜　6　｜　7　｜　8　｜\n");//linux
		System.out.print("  １　｜　２　｜　３　｜　４　｜　５　｜　６　｜　７　｜　８　｜\n");//Windows
		for(int j=11;j<19;j++){
			System.out.print(j-10+" |");
			for(int i=11;i<19;i++){
				switch (rock[i][j]){
					case 0:
						System.out.print("×");
						break;
					case 1 :
						System.out.print("●");
						break;
					case 2 :
						System.out.print("○");
						break;
					case -1 :
						System.out.print("▲");
						break;
					case -2 :
						System.out.print("△");
						break;
					default :
						System.out.print("??");
						System.out.println("Error:不明な値を検出");
				}
				System.out.print("　｜　");
			}
			System.out.println("");
			System.out.println("_______________________________________________________________");
		}
	}

	static boolean human()throws IOException{
		System.out.println("あなたは○です");
		System.out.println("打ちたいx座標を入力してください(パスの際は(15,15))");
		Scanner scanner = new Scanner (System.in);
		int i = (scanner.nextInt()+10);
		boolean en = true;
		if (i==10){
			en = false;
		}
		System.out.println("打ちたいy座標を入力してください(パスの際は(15,15))");
		int j = (scanner.nextInt()+10);
		rock[i][j]=2;
		int u=0;
		//右
		for (int k=0;k>=0;k++){
			if (rock[i+k][j]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i+k][j]==1){
				u=1;
			}
			if (u==1&&rock[i+k][j]==2){
				for(int s=0;s<k;s++){
					rock[i+s][j]=2;
				}
			}
		}
		//左
		for (int k=0;k>=0;k++){
			if (rock[i-k][j]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i-k][j]==1){
				u=1;
			}
			if (u==1&&rock[i-k][j]==2){
				for(int s=0;s<k;s++){
					rock[i-s][j]=2;
				}
			}
		}
		//上
		for (int k=0;k>=0;k++){
			if (rock[i][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i][j-k]==1){
				u=1;
			}
			if (u==1&&rock[i][j-k]==2){
				for(int s=0;s<k;s++){
					rock[i][j-s]=2;
				}
			}
		}
		//下
		for (int k=0;k>=0;k++){
			if (rock[i][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i][j+k]==1){
				u=1;
			}
			if (u==1&&rock[i][j+k]==2){
				for(int s=0;s<k;s++){
					rock[i][j+s]=2;
				}
			}
		}
		//右上
		for (int k=0;k>=0;k++){
			if (rock[i+k][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i+k][j-k]==1){
				u=1;
			}
			if (u==1&&rock[i+k][j-k]==2){
				for(int s=0;s<k;s++){
					rock[i+s][j-s]=2;
				}
			}
		}
		//右下
		for (int k=0;k>=0;k++){
			if (rock[i+k][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i+k][j+k]==1){
				u=1;
			}
			if (u==1&&rock[i+k][j+k]==2){
				for(int s=0;s<k;s++){
					rock[i+s][j+s]=2;
				}
			}
		}
		//左下
		for (int k=0;k>=0;k++){
			if (rock[i-k][j+k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i-k][j+k]==1){
				u=1;
			}
			if (u==1&&rock[i-k][j+k]==2){
				for(int s=0;s<k;s++){
					rock[i-s][j+s]=2;
				}
			}
		}
		//左上
		for (int k=0;k>=0;k++){
			if (rock[i-k][j-k]<=0||i-k==0||j-k==0){//白ではないまたは配列内が0にならないための処理
				break;//白がないので直上のfor文からぬける
			}
			if (rock[i-k][j-k]==1){
				u=1;
			}
			if (u==1&&rock[i-k][j-k]==2){
				for(int s=0;s<k;s++){
					rock[i-s][j-s]=2;
				}
			}
		}
		for(int s=11;s<19;s++){
			for(int t=11;t<19;t++){
				if (rock[t][s]==-1){
					rock[t][s]=0;
				}
			}
		}

		return (en);
	}

}
