// Generated code from Butter Knife. Do not modify!
package com.chunsoft.event;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Event_F$$ViewBinder<T extends com.chunsoft.event.Event_F> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099668, "field 'tv_schedule'");
    target.tv_schedule = finder.castView(view, 2131099668, "field 'tv_schedule'");
    view = finder.findRequiredView(source, 2131099667, "field 'tv_result'");
    target.tv_result = finder.castView(view, 2131099667, "field 'tv_result'");
    view = finder.findRequiredView(source, 2131099672, "field 'v_data'");
    target.v_data = view;
    view = finder.findRequiredView(source, 2131099740, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131099740, "field 'tv_title'");
    view = finder.findRequiredView(source, 2131099671, "field 'v_schedule'");
    target.v_schedule = view;
    view = finder.findRequiredView(source, 2131099669, "field 'tv_data'");
    target.tv_data = finder.castView(view, 2131099669, "field 'tv_data'");
    view = finder.findRequiredView(source, 2131099670, "field 'v_result'");
    target.v_result = view;
  }

  @Override public void unbind(T target) {
    target.tv_schedule = null;
    target.tv_result = null;
    target.v_data = null;
    target.tv_title = null;
    target.v_schedule = null;
    target.tv_data = null;
    target.v_result = null;
  }
}
