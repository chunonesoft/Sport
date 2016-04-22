// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Statistics_FA$$ViewBinder<T extends com.chunsoft.match.Statistics_FA> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099751, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099751, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099711, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = finder.castView(view, 2131099711, "field 'mSwipeRefreshLayout'");
    view = finder.findRequiredView(source, 2131099712, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131099712, "field 'mWebView'");
  }

  @Override public void unbind(T target) {
    target.tv_title = null;
    target.mSwipeRefreshLayout = null;
    target.mWebView = null;
  }
}
