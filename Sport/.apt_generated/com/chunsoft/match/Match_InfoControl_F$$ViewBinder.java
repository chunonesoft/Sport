// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Match_InfoControl_F$$ViewBinder<T extends com.chunsoft.match.Match_InfoControl_F> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099689, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = finder.castView(view, 2131099689, "field 'mSwipeRefreshLayout'");
    view = finder.findRequiredView(source, 2131099690, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131099690, "field 'mWebView'");
  }

  @Override public void unbind(T target) {
    target.mSwipeRefreshLayout = null;
    target.mWebView = null;
  }
}
