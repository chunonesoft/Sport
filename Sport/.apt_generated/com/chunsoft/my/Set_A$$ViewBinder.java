// Generated code from Butter Knife. Do not modify!
package com.chunsoft.my;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Set_A$$ViewBinder<T extends com.chunsoft.my.Set_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099759, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099759, "field 'tv_title'");
  }

  @Override public void unbind(T target) {
    target.tv_title = null;
  }
}
