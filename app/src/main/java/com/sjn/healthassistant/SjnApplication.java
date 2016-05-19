package com.sjn.healthassistant;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sjn.healthassistant.inject.component.AppComponent;
import com.sjn.healthassistant.inject.component.DaggerAppComponent;
import com.sjn.healthassistant.inject.module.AppModule;
import com.sjn.healthassistant.pojo.Department;
import com.sjn.healthassistant.pojo.Drug;
import com.sjn.healthassistant.pojo.DrugClassify;
import com.sjn.healthassistant.pojo.HealthClassify;
import com.sjn.healthassistant.util.ImageLoadUtil;

import java.io.IOException;
import java.io.InputStream;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * Created by sjn on 16/4/19.
 */
public class SjnApplication extends Application {


    private static AppComponent appComponent;

    public static Context getContext() {
        return sContext;
    }

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        ImageLoadUtil.init(getApplicationContext());
        initDatabase();
        initAppComponet();
    }


    private void initAppComponet() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }


    /**
     * 分类数据本地存储
     */
    private void initDatabase() {
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
        Realm realm = Realm.getDefaultInstance();
        if (realm.where(HealthClassify.class).findAll().size() == 0) {
            String json = "[{\"description\":\"减肥瘦身,美丽一生,减肥资讯 瘦身资讯 ,减肥瘦身健康知识,减肥瘦身信息专题\",\"id\":11,\"keywords\":\"减肥瘦身\",\"name\":\"减肥瘦身\",\"seq\":1,\"title\":\"减肥瘦身\"},{\"description\":\"私密生活 两性健康 两性健康知识 两性专题 性生活知识 私密资讯 \",\"id\":7,\"keywords\":\"私密生活\",\"name\":\"私密生活\",\"seq\":2,\"title\":\"私密生活\"},{\"description\":\"女性保养 女性健康资讯 女性生活 女性健康 注意女性健康 女性专题 女性健康网\",\"id\":5,\"keywords\":\"女性保养\",\"name\":\"女性保养\",\"seq\":3,\"title\":\"女性保养\"},{\"description\":\"男性健康 男性健康知识 男性健康资讯 男性专题 男性健康专题\",\"id\":4,\"keywords\":\"男性健康\",\"name\":\"男性健康\",\"seq\":4,\"title\":\"男性健康\"},{\"description\":\"孕婴手册 孕妇健康 怀孕健康知识 育儿健康 孕婴专题 孕婴健康专题\",\"id\":6,\"keywords\":\"孕婴手册\",\"name\":\"孕婴手册\",\"seq\":5,\"title\":\"孕婴手册\"},{\"description\":\"夫妻关系 夫妻情感 两性情感 健康情感 夫妻生活 夫妻专题 两性专题\",\"id\":13,\"keywords\":\"夫妻情感\",\"name\":\"夫妻情感\",\"seq\":5,\"title\":\"夫妻情感\"},{\"description\":\"育儿宝典 婴儿护理 教育 健康知识 育儿知识 \",\"id\":8,\"keywords\":\"育儿宝典\",\"name\":\"育儿宝典\",\"seq\":6,\"title\":\"育儿宝典\"},{\"description\":\"健康饮食 食品健康 饮食健康 生活饮食 食品知识   饮食专题 \",\"id\":3,\"keywords\":\"健康饮食\",\"name\":\"健康饮食\",\"seq\":7,\"title\":\"健康饮食\"},{\"description\":\"医疗护理 生活健康  预防疾病 疾病治疗 医疗知识 护理知识 \",\"id\":12,\"keywords\":\"医疗护理\",\"name\":\"医疗护理\",\"seq\":7,\"title\":\"医疗护理\"},{\"description\":\"老人健康 老人护理 老人生活 长寿知识 老人专题\",\"id\":1,\"keywords\":\"老人健康\",\"name\":\"老人健康\",\"seq\":8,\"title\":\"老人健康\"},{\"description\":\"孩子健康 孩子教育 孩子生活 孩子成长 孩子健康专题\",\"id\":2,\"keywords\":\"孩子健康\",\"name\":\"孩子健康\",\"seq\":8,\"title\":\"孩子健康\"},{\"description\":\"四季养生 春节养生 夏季养生 秋季养生 冬季养生 养生健康知识 养生专题 \",\"id\":10,\"keywords\":\"四季养生\",\"name\":\"四季养生\",\"seq\":10,\"title\":\"四季养生\"},{\"description\":\"心里健康 心里疾病 心里医疗 心里咨询 健康知识 \",\"id\":9,\"keywords\":\"心里健康\",\"name\":\"心里健康\",\"seq\":11,\"title\":\"心里健康\"}]";
            realm.beginTransaction();
            realm.createAllFromJson(HealthClassify.class, json);
            realm.commitTransaction();
        }
        if (realm.where(DrugClassify.class).findAll().size() == 0) {
            String json = "[{\"description\":\"性病用药,性病用药药品,性病用药相关药品,治疗性病用药相关功能的药品,性病用药相关药品查询,性病用药药品库\",\"drugclass\":0,\"id\":1,\"keywords\":\"性病用药\",\"name\":\"性病用药\",\"seq\":0,\"title\":\"性病用药\"},{\"description\":\"抗结核及麻风类,抗结核及麻风类药品,抗结核及麻风类相关药品,治疗抗结核及麻风类相关功能的药品,抗结核及麻风类相关药品查询,抗结核及麻风类药品库\",\"drugclass\":0,\"id\":2,\"keywords\":\"抗结核及麻风类\",\"name\":\"抗结核及麻风类\",\"seq\":0,\"title\":\"抗结核及麻风类\"},{\"description\":\"血液疾病类,血液疾病类药品,血液疾病类相关药品,治疗血液疾病类相关功能的药品,血液疾病类相关药品查询,血液疾病类药品库\",\"drugclass\":0,\"id\":3,\"keywords\":\"血液疾病类\",\"name\":\"血液疾病类\",\"seq\":0,\"title\":\"血液疾病类\"},{\"description\":\"水电解质及酸碱,水电解质及酸碱药品,水电解质及酸碱相关药品,治疗水电解质及酸碱相关功能的药品,水电解质及酸碱相关药品查询,水电解质及酸碱药品库\",\"drugclass\":0,\"id\":4,\"keywords\":\"水电解质及酸碱\",\"name\":\"水电解质及酸碱\",\"seq\":0,\"title\":\"水电解质及酸碱\"},{\"description\":\"抗寄生虫类,抗寄生虫类药品,抗寄生虫类相关药品,治疗抗寄生虫类相关功能的药品,抗寄生虫类相关药品查询,抗寄生虫类药品库\",\"drugclass\":0,\"id\":5,\"keywords\":\"抗寄生虫类\",\"name\":\"抗寄生虫类\",\"seq\":0,\"title\":\"抗寄生虫类\"},{\"description\":\"风湿免疫科,风湿免疫科药品,风湿免疫科相关药品,治疗风湿免疫科相关功能的药品,风湿免疫科相关药品查询,风湿免疫科药品库\",\"drugclass\":0,\"id\":6,\"keywords\":\"风湿免疫科\",\"name\":\"风湿免疫科\",\"seq\":0,\"title\":\"风湿免疫科\"},{\"description\":\"肿瘤科,肿瘤科药品,肿瘤科相关药品,治疗肿瘤科相关功能的药品,肿瘤科相关药品查询,肿瘤科药品库\",\"drugclass\":0,\"id\":7,\"keywords\":\"肿瘤科\",\"name\":\"肿瘤科\",\"seq\":0,\"title\":\"肿瘤科\"},{\"description\":\"神经/精神,神经/精神药品,神经/精神相关药品,治疗神经/精神相关功能的药品,神经/精神相关药品查询,神经/精神药品库\",\"drugclass\":0,\"id\":8,\"keywords\":\"神经/精神\",\"name\":\"神经/精神\",\"seq\":0,\"title\":\"神经/精神\"},{\"description\":\"内分泌失常,内分泌失常药品,内分泌失常相关药品,治疗内分泌失常相关功能的药品,内分泌失常相关药品查询,内分泌失常药品库\",\"drugclass\":0,\"id\":9,\"keywords\":\"内分泌失常\",\"name\":\"内分泌失常\",\"seq\":0,\"title\":\"内分泌失常\"},{\"description\":\"肾病,肾病药品,肾病相关药品,治疗肾病相关功能的药品,肾病相关药品查询,肾病药品库\",\"drugclass\":0,\"id\":10,\"keywords\":\"肾病\",\"name\":\"肾病\",\"seq\":0,\"title\":\"肾病\"},{\"description\":\"肝胆胰用药,肝胆胰用药药品,肝胆胰用药相关药品,治疗肝胆胰用药相关功能的药品,肝胆胰用药相关药品查询,肝胆胰用药药品库\",\"drugclass\":0,\"id\":11,\"keywords\":\"肝胆胰用药\",\"name\":\"肝胆胰用药\",\"seq\":0,\"title\":\"肝胆胰用药\"},{\"description\":\"心脑血管,心脑血管药品,心脑血管相关药品,治疗心脑血管相关功能的药品,心脑血管相关药品查询,心脑血管药品库\",\"drugclass\":0,\"id\":12,\"keywords\":\"心脑血管\",\"name\":\"心脑血管\",\"seq\":0,\"title\":\"心脑血管\"},{\"description\":\"维生素及营养类,维生素及营养类药品,维生素及营养类相关药品,治疗维生素及营养类相关功能的药品,维生素及营养类相关药品查询,维生素及营养类药品库\",\"drugclass\":0,\"id\":13,\"keywords\":\"维生素及营养类\",\"name\":\"维生素及营养类\",\"seq\":0,\"title\":\"维生素及营养类\"},{\"description\":\"儿科用药,儿科用药药品,儿科用药相关药品,治疗儿科用药相关功能的药品,儿科用药相关药品查询,儿科用药药品库\",\"drugclass\":0,\"id\":14,\"keywords\":\"儿科用药\",\"name\":\"儿科用药\",\"seq\":0,\"title\":\"儿科用药\"},{\"description\":\"妇科用药,妇科用药药品,妇科用药相关药品,治疗妇科用药相关功能的药品,妇科用药相关药品查询,妇科用药药品库\",\"drugclass\":0,\"id\":15,\"keywords\":\"妇科用药\",\"name\":\"妇科用药\",\"seq\":0,\"title\":\"妇科用药\"},{\"description\":\"男科用药,男科用药药品,男科用药相关药品,治疗男科用药相关功能的药品,男科用药相关药品查询,男科用药药品库\",\"drugclass\":0,\"id\":16,\"keywords\":\"男科用药\",\"name\":\"男科用药\",\"seq\":0,\"title\":\"男科用药\"},{\"description\":\"家庭常备,家庭常备药品,家庭常备相关药品,治疗家庭常备相关功能的药品,家庭常备相关药品查询,家庭常备药品库\",\"drugclass\":0,\"id\":17,\"keywords\":\"家庭常备\",\"name\":\"家庭常备\",\"seq\":0,\"title\":\"家庭常备\"},{\"description\":\"呼吸系统类,呼吸系统类药品,呼吸系统类相关药品,治疗呼吸系统类相关功能的药品,呼吸系统类相关药品查询,呼吸系统类药品库\",\"drugclass\":0,\"id\":18,\"keywords\":\"呼吸系统类\",\"name\":\"呼吸系统类\",\"seq\":0,\"title\":\"呼吸系统类\"},{\"description\":\"五官用药,五官用药药品,五官用药相关药品,治疗五官用药相关功能的药品,五官用药相关药品查询,五官用药药品库\",\"drugclass\":0,\"id\":19,\"keywords\":\"五官用药\",\"name\":\"五官用药\",\"seq\":0,\"title\":\"五官用药\"},{\"description\":\"肠胃用药,肠胃用药药品,肠胃用药相关药品,治疗肠胃用药相关功能的药品,肠胃用药相关药品查询,肠胃用药药品库\",\"drugclass\":0,\"id\":20,\"keywords\":\"肠胃用药\",\"name\":\"肠胃用药\",\"seq\":0,\"title\":\"肠胃用药\"},{\"description\":\"皮肤用药,皮肤用药药品,皮肤用药相关药品,治疗皮肤用药相关功能的药品,皮肤用药相关药品查询,皮肤用药药品库\",\"drugclass\":0,\"id\":21,\"keywords\":\"皮肤用药\",\"name\":\"皮肤用药\",\"seq\":0,\"title\":\"皮肤用药\"},{\"description\":\"感冒发热,感冒发热药品,感冒发热相关药品,治疗感冒发热相关功能的药品,感冒发热相关药品查询,感冒发热药品库\",\"drugclass\":0,\"id\":22,\"keywords\":\"感冒发热\",\"name\":\"感冒发热\",\"seq\":0,\"title\":\"感冒发热\"},{\"description\":\"手术用药,手术用药药品,手术用药相关药品,治疗手术用药相关功能的药品,手术用药相关药品查询,手术用药药品库\",\"drugclass\":0,\"id\":203,\"keywords\":\"手术用药\",\"name\":\"手术用药\",\"seq\":1,\"title\":\"手术用药\"},{\"description\":\"其他,其他药品,其他相关药品,治疗其他相关功能的药品,其他相关药品查询,其他药品库\",\"drugclass\":0,\"id\":201,\"keywords\":\"其他\",\"name\":\"其他\",\"seq\":6,\"title\":\"其他\"}]";
            realm.beginTransaction();
            realm.createAllFromJson(DrugClassify.class, json);
            realm.commitTransaction();
        }
        if (realm.where(Department.class).findAll().size() == 0) {
            String json = "[{id:1,name: \"内科\"},{id: 10,name: \"外科\"},{id: 23,name: \"手外科\"},{id: 25,name: \"妇产科\"},{id: 28,name: \"五官科\"},{id: 32,name: \"皮肤性病\"},{id: 35,name: \"中西医结合科\"},{id: 37,name: \"肝病\"},{id: 39,name: \"精神心理科\"},{id: 42,name: \"儿科\"},{id: 44,name: \"男科\"},{id: 46,name: \"生殖健康\"},{id: 50,name: \"传染科\"},{id: 52,name: \"老年科\"},{id: 54,name: \"体检保健科\"},{id: 62,name: \"营养科\"},{id: 56,name: \"成瘾医学科\"}]";
            realm.beginTransaction();
            realm.createAllFromJson(Department.class, json);
            realm.commitTransaction();
        }
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
