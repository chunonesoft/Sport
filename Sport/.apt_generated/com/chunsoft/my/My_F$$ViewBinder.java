// Generated code from Butter Knife. Do not modify!
package com.chunsoft.my;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class My_F$$ViewBinder<T extends com.chunsoft.my.My_F> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099734, "field 'll_exit'");
    target.ll_exit = finder.castView(view, 2131099734, "field 'll_exit'");
    view = finder.findRequiredView(source, 2131099731, "field 'll_focus'");
    target.ll_focus = finder.castView(view, 2131099731, "field 'll_focus'");
    view = finder.findRequiredView(source, 2131099759, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099759, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099733, "field 'll_set'");
    target.ll_set = finder.castView(view, 2131099733, "field 'll_set'");
    view = finder.findRequiredView(source, 2131099732, "field 'll_checkUpdate'");
    target.ll_checkUpdate = finder.castView(view, 2131099732, "field 'll_checkUpdate'");
  }

  @Override public void unbind(T target) {
    target.ll_exit = null;
    target.ll_focus = null;
    target.tv_title = null;
    target.ll_set = null;
    target.ll_checkUpdate = null;
  }
}
