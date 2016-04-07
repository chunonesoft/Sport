// Generated code from Butter Knife. Do not modify!
package com.chunsoft.my;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Login_A$$ViewBinder<T extends com.chunsoft.my.Login_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099675, "field 'et_password'");
    target.et_password = finder.castView(view, 2131099675, "field 'et_password'");
    view = finder.findRequiredView(source, 2131099678, "field 'tv_wjmm'");
    target.tv_wjmm = finder.castView(view, 2131099678, "field 'tv_wjmm'");
    view = finder.findRequiredView(source, 2131099674, "field 'et_mobile'");
    target.et_mobile = finder.castView(view, 2131099674, "field 'et_mobile'");
    view = finder.findRequiredView(source, 2131099677, "field 'tv_register'");
    target.tv_register = finder.castView(view, 2131099677, "field 'tv_register'");
    view = finder.findRequiredView(source, 2131099676, "field 'btn_login'");
    target.btn_login = finder.castView(view, 2131099676, "field 'btn_login'");
  }

  @Override public void unbind(T target) {
    target.et_password = null;
    target.tv_wjmm = null;
    target.et_mobile = null;
    target.tv_register = null;
    target.btn_login = null;
  }
}
