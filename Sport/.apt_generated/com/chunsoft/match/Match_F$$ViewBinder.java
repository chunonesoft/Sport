// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Match_F$$ViewBinder<T extends com.chunsoft.match.Match_F> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099694, "field 'myLv'");
    target.myLv = finder.castView(view, 2131099694, "field 'myLv'");
  }

  @Override public void unbind(T target) {
    target.myLv = null;
  }
}
