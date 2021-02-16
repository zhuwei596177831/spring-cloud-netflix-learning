package irule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 朱伟伟
 * @date 2021-02-14 14:50:35
 * @description 针对某个服务自定义负载均衡配置 不能放在主启动类所在包下
 */
public class EurekaClientRibbonConfig {

    @Bean
    public IRule iRule() {
        return new RandomRule();
    }

}
