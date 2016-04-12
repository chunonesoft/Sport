// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Analysis_A$$ViewBinder<T extends com.chunsoft.match.Analysis_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099661, "field 'tv_ball'");
    target.tv_ball = finder.castView(view, 2131099661, "field 'tv_ball'");
    view = finder.findRequiredView(source, 2131099665, "field 'v_statistics'");
    target.v_statistics = view;
    view = finder.findRequiredView(source, 2131099662, "field 'tv_statistics'");
    target.tv_statistics = finder.castView(view, 2131099662, "field 'tv_statistics'");
    view = finder.findRequiredView(source, 2131099660, "field 'tv_index'");
    target.tv_index = finder.castView(view, 2131099660, "field 'tv_index'");
    view = finder.findRequiredView(source, 2131099741, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099741, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099663, "field 'v_index'");
    target.v_index = view;
    view = finder.findRequiredView(source, 2131099664, "field 'v_ball'");
    target.v_ball = view;
  }

  @Override public void unbind(T target) {
    target.tv_ball = null;
    target.v_statistics = null;
    target.tv_statistics = null;
    target.tv_index = null;
    target.tv_title = null;
    target.v_index = null;
    target.v_ball = null;
  }
}
