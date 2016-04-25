package com.sjn.healthassistant.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.sjn.healthassistant.R;
import com.sjn.healthassistant.common.UrlImageGetter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.content)
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews(){
        String html ="<p><br/></p><p><img alt=\"我国全面禁止胎儿性别鉴定 违者最高罚3万\" src=\"http://tnfs.tngou.net/img/info/160424/59e4aa8a9fe69bf333ab548ec69e22fb.jpg\" height=\"274\" width=\"468\"/></p><p>本报讯（记者 彭小菲）下月起，我国将禁止任何单位或者个人介绍、组织孕妇实施非医学需要的胎儿性别鉴定和选择性别人工终止妊娠。昨天，记者从国家卫计委获悉，新修订的《禁止非医学需要的胎儿性别鉴定和选择性别人工终止妊娠的规定》已正式出台，将于今年5月1日起施行。</p>据国家卫计委透露，自上世纪80年代以来，我国出生人口性别比已持续30多年处于偏高状态。经过多年努力，出生人口性别比持续升高的势头得到初步遏制，但2015年仍高达113.51。据了解，新修订的《规定》中明确了非医学需要的胎儿性别鉴定和选择性别人工终止妊娠管理制度。禁止“两非”行为，同时根据《母婴<a target=\"_blank\" href=\"http://www.tngou.net/disease/show/6816\">保健</a>法》，结合工作实际，对符合法定生育条件实施选择性别人工终止妊娠的情形作出规定：即符合法定生育条件，除胎儿患严重遗传性疾病的;胎儿有严重缺陷的;因患严重疾病，继续妊娠可能危及孕妇生命安全或者严重危害孕妇健康的;法律法规规定的或医学上认为确有必要终止妊娠的其他情形之外，均不得实施选择性别人工终止妊娠。<p>新规还提出，建立终止妊娠药品以及超声诊断仪、<a target=\"_blank\" href=\"http://www.tngou.net/check/show/2704\">染色体</a>检测专用设备等医疗器械管理制度。对终止妊娠药品实行目录管理，建立终止妊娠药品销售、采购、使用登记制度，禁止药品零售企业销售终止妊娠药品。</p><p>值得注意的是，新规中明确对介绍、组织孕妇实施非医学需要的胎儿性别鉴定和选择性别人工终止妊娠的，由县级以上卫生计生行政部门责令改正，给予警告；情节严重的，没收违法所得，并处5000元以上3万元以下罚款。医疗器械生产经营企业将超声诊断仪、染色体检测专用设备等医疗器械销售给无购买资质的机构或者个人的，由县级以上食品药品监管部门责令改正，处1万元以上3万元以下罚款。</p><p><br/></p><br/>";
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        content.setText(Html.fromHtml(html,new UrlImageGetter(content),null));

    }
}
