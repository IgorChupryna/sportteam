package spring.basic.domain.autowiring.annotations.byName;


import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("webPage")
public class WebPage {
    @Resource(name="webPageUser")
    private WebPageUser user;

    @Override
    public String toString() {
        return "WebPage{" +
                "user=" + user +
                '}';
    }
}
