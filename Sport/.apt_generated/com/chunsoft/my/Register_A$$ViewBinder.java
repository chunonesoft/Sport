// Generated code from Butter Knife. Do not modify!
package com.chunsoft.my;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Register_A$$ViewBinder<T extends com.chunsoft.my.Register_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099740, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099740, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099730, "field 'et_name'");
    target.et_name = finder.castView(view, 2131099730, "field 'et_name'");
    view = finder.findRequiredView(source, 2131099731, "field 'et_mail'");
    target.et_mail = finder.castView(view, 2131099731, "field 'et_mail'");
    view = finder.findRequiredView(source, 2131099728, "field 'et_checknum'");
    target.et_checknum = finder.castView(view, 2131099728, "field 'et_checknum'");
  }

  @Override public void unbind(T target) {
    target.tv_title = null;
    target.et_name = null;
    target.et_mail = null;
    target.et_checknum = null;
  }
}
