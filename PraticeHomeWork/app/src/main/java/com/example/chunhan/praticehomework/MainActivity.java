package com.example.chunhan.praticehomework;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private Context mContext;
	public GridLayout mGridLayout;
	public int totalSteps = 0;
	int[][] OorX = new int[][]{{0,0,0},{0,0,0},{0,0,0}};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this.getApplicationContext();
		mGridLayout = (GridLayout)findViewById(R.id.GridTable);
		for(int i=0;i<9;i++){
			final ImageView mGridImage = (ImageView)mGridLayout.getChildAt(i);
			final int Inum = i;
			mGridImage.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if(totalSteps < 9){
						if(totalSteps % 2 == 1){
							mGridImage.setImageResource(R.drawable.mix_xx_drawabke);
							OorX[( Inum / 3 )][ Inum % 3 ] = 1;
							mGridImage.setClickable(false);
							totalSteps++;
						}else
						if(totalSteps % 2 == 0){
							mGridImage.setImageResource(R.drawable.mix_oo_drawable);
							OorX[( Inum / 3 )][ Inum % 3 ] = 10;
							mGridImage.setClickable(false);
							totalSteps++;
						}
						Animation am = new RotateAnimation(0 , 360 , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
						mGridImage.setAnimation(am);
						am.setDuration(500);
						am.setFillBefore(true);
						am.startNow();
						am.setAnimationListener(new Animation.AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {
							}

							@Override
							public void onAnimationEnd(Animation animation) {
								animation.cancel();
							}

							@Override
							public void onAnimationRepeat(Animation animation) {
							}
						});
						whoWin(totalSteps);
					}
				}
			});
		}
	}

	private void whoWin(int totalSteps) {
		int sumRow = 0;//橫線
		int sumCol = 0;//直線
		int sumSla1 = 0;//左往右斜線
		int sumSla2 = 0;//右往左斜線
		boolean notDraw = false;

		labelFinish:{
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					sumRow = sumRow + OorX[i][j];
					sumCol = sumCol + OorX[j][i];
					if(i == j)
						sumSla1 = sumSla1 + OorX[i][j];
					if((i + j) == 2)
						sumSla2 = sumSla2 + OorX[i][j];
					if(sumRow / 30 == 1 || sumCol/30 == 1||sumSla1 / 30 == 1 || sumSla2 / 30 == 1){
						Toast toast = Toast.makeText(mContext,  "O Win!!!", Toast.LENGTH_SHORT);
						toast.show();
						totalSteps = 9;
						notDraw = true;
						break labelFinish;
					}else
						if((sumRow % 30) / 3 == 1 || (sumCol % 30) / 3 == 1 || (sumSla1 % 30) / 3 == 1 || (sumSla2 % 30) / 3 == 1){
							Toast toast = Toast.makeText(mContext,  "X Win!!!", Toast.LENGTH_SHORT);
							toast.show();
							totalSteps = 9;
							notDraw = true;
							break labelFinish;
						}
				}
				sumRow = 0;
				sumCol = 0;
			}
		}

		if(totalSteps == 9){
			if(notDraw == false){
				Toast toast = Toast.makeText(mContext,  "Draw!!!", Toast.LENGTH_SHORT);
				toast.show();
			}else {
				Toast toast = Toast.makeText(mContext, "GAME OVER & RESTART!", Toast.LENGTH_SHORT);
				toast.show();
			}
			playAgain();
		}

	}

	private void playAgain() {

		for(int i=0;i<9;i++){
			final ImageView mGridImage = (ImageView)mGridLayout.getChildAt(i);
			mGridImage.setClickable(true);
			mGridImage.setImageResource(R.drawable.sqline);
			OorX = new int[][]{{0,0,0},{0,0,0},{0,0,0}};
			totalSteps = 0;

		}

	}


}
