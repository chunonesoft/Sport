// Generated code from Butter Knife. Do not modify!
package com.chunsoft.my;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Register_A$$ViewBinder<T extends com.chunsoft.my.Register_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099731, "field 'et_name'");
    target.et_name = finder.castView(view, 2131099731, "field 'et_name'");
    view = finder.findRequiredView(source, 2131099733, "field 'et_pwd'");
    target.et_pwd = finder.castView(view, 2131099733, "field 'et_pwd'");
    view = finder.findRequiredView(source, 2131099732, "field 'et_mail'");
    target.et_mail = finder.castView(view, 2131099732, "field 'et_mail'");
    view = finder.findRequiredView(source, 2131099729, "field 'et_checknum'");
    target.et_checknum = finder.castView(view, 2131099729, "field 'et_checknum'");
    view = finder.findRequiredView(source, 2131099735, "field 'btn_next'");
    target.btn_next = finder.castView(view, 2131099735, "field 'btn_next'");
    view = finder.findRequiredView(source, 2131099727, "field 'et_num'");
    target.et_num = finder.castView(view, 2131099727, "field 'et_num'");
    view = finder.findRequiredView(source, 2131099734, "field 'et_apwd'");
    target.et_apwd = finder.castView(view, 2131099734, "field 'et_apwd'");
    view = finder.findRequiredView(source, 2131099744, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099744, "field 'tv_title'");
  }

  @Override public void unbind(T target) {
    target.et_name = null;
    target.et_pwd = null;
    target.et_mail = null;
    target.et_checknum = null;
    target.btn_next = null;
    target.et_num = null;
    target.et_apwd = null;
    target.tv_title = null;
  }
}
