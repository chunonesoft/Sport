// Generated code from Butter Knife. Do not modify!
package com.chunsoft.event;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Event_Result$$ViewBinder<T extends com.chunsoft.event.Event_Result> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099673, "field 'lv'");
    target.lv = finder.castView(view, 2131099673, "field 'lv'");
  }

  @Override public void unbind(T target) {
    target.lv = null;
  }
}
