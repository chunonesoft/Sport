// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Match_ShowBigdata_A$$ViewBinder<T extends com.chunsoft.match.Match_ShowBigdata_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099711, "field 'mSwipeRefreshLayout'");
    target.mSwipeRefreshLayout = finder.castView(view, 2131099711, "field 'mSwipeRefreshLayout'");
    view = finder.findRequiredView(source, 2131099712, "field 'mWebView'");
    target.mWebView = finder.castView(view, 2131099712, "field 'mWebView'");
    view = finder.findRequiredView(source, 2131099751, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099751, "field 'tv_title'");
  }

  @Override public void unbind(T target) {
    target.mSwipeRefreshLayout = null;
    target.mWebView = null;
    target.tv_title = null;
  }
}
