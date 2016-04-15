// Generated code from Butter Knife. Do not modify!
package com.chunsoft.my;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Login_A$$ViewBinder<T extends com.chunsoft.my.Login_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099677, "field 'et_password'");
    target.et_password = finder.castView(view, 2131099677, "field 'et_password'");
    view = finder.findRequiredView(source, 2131099744, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099744, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099680, "field 'tv_wjmm'");
    target.tv_wjmm = finder.castView(view, 2131099680, "field 'tv_wjmm'");
    view = finder.findRequiredView(source, 2131099679, "field 'tv_register'");
    target.tv_register = finder.castView(view, 2131099679, "field 'tv_register'");
    view = finder.findRequiredView(source, 2131099678, "field 'btn_login'");
    target.btn_login = finder.castView(view, 2131099678, "field 'btn_login'");
    view = finder.findRequiredView(source, 2131099676, "field 'et_mobile'");
    target.et_mobile = finder.castView(view, 2131099676, "field 'et_mobile'");
  }

  @Override public void unbind(T target) {
    target.et_password = null;
    target.tv_title = null;
    target.tv_wjmm = null;
    target.tv_register = null;
    target.btn_login = null;
    target.et_mobile = null;
  }
}
