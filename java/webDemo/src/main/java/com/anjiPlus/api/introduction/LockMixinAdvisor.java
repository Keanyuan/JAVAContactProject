package com.anjiPlus.api.introduction;

import org.aopalliance.aop.Advice;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

    private static final long serialVersionUID = -4959338167131447600L;

    public LockMixinAdvisor() {
        super(new LockMixin(), Lockable.class);
    }
}
