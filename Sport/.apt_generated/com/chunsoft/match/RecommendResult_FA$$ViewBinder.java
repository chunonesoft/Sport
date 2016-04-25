// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RecommendResult_FA$$ViewBinder<T extends com.chunsoft.match.RecommendResult_FA> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099752, "field 'result_x_lv'");
    target.result_x_lv = finder.castView(view, 2131099752, "field 'result_x_lv'");
    view = finder.findRequiredView(source, 2131099751, "field 'btn_search'");
    target.btn_search = finder.castView(view, 2131099751, "field 'btn_search'");
    view = finder.findRequiredView(source, 2131099753, "field 'tv_winNum'");
    target.tv_winNum = finder.castView(view, 2131099753, "field 'tv_winNum'");
    view = finder.findRequiredView(source, 2131099759, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099759, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099750, "field 'btn_end'");
    target.btn_end = finder.castView(view, 2131099750, "field 'btn_end'");
    view = finder.findRequiredView(source, 2131099754, "field 'tv_winRate'");
    target.tv_winRate = finder.castView(view, 2131099754, "field 'tv_winRate'");
    view = finder.findRequiredView(source, 2131099749, "field 'btn_start'");
    target.btn_start = finder.castView(view, 2131099749, "field 'btn_start'");
  }

  @Override public void unbind(T target) {
    target.result_x_lv = null;
    target.btn_search = null;
    target.tv_winNum = null;
    target.tv_title = null;
    target.btn_end = null;
    target.tv_winRate = null;
    target.btn_start = null;
  }
}
