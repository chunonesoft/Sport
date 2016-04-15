// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RecommendResult_FA$$ViewBinder<T extends com.chunsoft.match.RecommendResult_FA> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099737, "field 'btn_end'");
    target.btn_end = finder.castView(view, 2131099737, "field 'btn_end'");
    view = finder.findRequiredView(source, 2131099738, "field 'result_x_lv'");
    target.result_x_lv = finder.castView(view, 2131099738, "field 'result_x_lv'");
    view = finder.findRequiredView(source, 2131099744, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099744, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099736, "field 'btn_start'");
    target.btn_start = finder.castView(view, 2131099736, "field 'btn_start'");
  }

  @Override public void unbind(T target) {
    target.btn_end = null;
    target.result_x_lv = null;
    target.tv_title = null;
    target.btn_start = null;
  }
}
