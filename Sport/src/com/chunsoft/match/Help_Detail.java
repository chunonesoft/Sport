package com.chunsoft.match;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.chunsoft.sport.R;

public class Help_Detail extends Activity {
	private TextView tv_detail_title;
	private TextView tv_detail_content;
	private int type;
	private String title;
	private String titles[] = { "欧洲盘赔率", "亚盘赔率 ", "走盘", "水位", "升盘降盘" };
	private String contents[] = {
			"    足彩公司会分析球员状态、战术打法、球队实力等等，经过一系列计算，得出胜、平、负三种结果的不同概率，即开出赔率。根据高风险高回报的原则，赢面大的球队，赔率肯定低，相对弱的队，赔率自然就高。因此押冷门如果成功会有相当高的回报，风险也更大，足彩公司一般每场比赛抽水5%到10%。",
			"    亚盘起源于东南亚，通过让球的方式来拉平比赛双方在客观上存在的实力差距。什么叫让球呢？就是如果两个队实力悬殊，那么实力较强的一方不光要赢，还要达到一定数量的进球数。几种简单的情况:"
					+ "盘口 意思解释"
					+ "\n平手(0) 双方平开，哪方获胜即全赢，打平则退还注金"
					+ "\n让平半球（0.25）让球方获胜即全赢，打平则输一半，负则全输"
					+ "\n让半球(0.5) 让球方打平或者输球买它的全输，赢一个全赢"
					+ "\n让半一球（0.75）让球方打平或者输球则全输，赢一个赢一半，赢两个全赢"
					+ "\n让一球(1) 让球方输、平全输，赢一个球算平，赢两球全赢"
					+ "\n让一球球半（1.25）让球方赢两球以上即全赢，赢一个输一半，打平或负则全输"
					+ "\n让一球半(1.5) 让球方赢两球以上即全赢，赢一个、打平或负则全输"
					+ "\n让球半两球（1.75）让球方赢三球以上即全赢，赢两个赢一半，赢一个、打平或负则全输"
					+ "\n让两球(2) 让球方赢两个球算平，赢三个球全赢，赢一个、打平或负则全输"
					+ "\n上下盘：让球的一方称为上盘，受让的一方称为下盘。",
			"    我们刚刚说过让球盘口的问题，比如平手盘中两队打平、让一球净胜一球、让两球净胜两球，这类情况均为平局，投注上下盘均无胜负，退还本金，结账回家，即为“走盘”。",
			"    水位就是投注参与者的盈钱和输钱的系数",
			"    亚盘向上浮动一个盘口称为升盘，反之则为降盘。比如，主队由最初的让一球半上升到球半/两球，即为升盘，主队由让一球半降到一球/球半，为降盘。亚盘的盘口升降和欧赔的赔率升降同理，都是受到投注变化和信息因素的影响下产生。" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpdetails);
		tv_detail_content = (TextView) findViewById(R.id.tv_detail_content);
		tv_detail_title = (TextView) findViewById(R.id.tv_detail_title);
		type = getIntent().getIntExtra("type", 0);
		tv_detail_title.setText(titles[type]);
		tv_detail_content.setText(contents[type]);
	}
}
