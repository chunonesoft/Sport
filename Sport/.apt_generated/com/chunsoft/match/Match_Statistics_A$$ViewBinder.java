// Generated code from Butter Knife. Do not modify!
package com.chunsoft.match;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Match_Statistics_A$$ViewBinder<T extends com.chunsoft.match.Match_Statistics_A> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099725, "field 'v_recommend'");
    target.v_recommend = view;
    view = finder.findRequiredView(source, 2131099759, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099759, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099722, "field 'tv_infoControl'");
    target.tv_infoControl = finder.castView(view, 2131099722, "field 'tv_infoControl'");
    view = finder.findRequiredView(source, 2131099724, "field 'v_infoControl'");
    target.v_infoControl = view;
    view = finder.findRequiredView(source, 2131099723, "field 'tv_recommend'");
    target.tv_recommend = finder.castView(view, 2131099723, "field 'tv_recommend'");
  }

  @Override public void unbind(T target) {
    target.v_recommend = null;
    target.tv_title = null;
    target.tv_infoControl = null;
    target.v_infoControl = null;
    target.tv_recommend = null;
  }
}
