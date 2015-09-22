package kz.growit.smartservice3.fab;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jean on 9/22/2015.
 */
public class FloatingActionButtonBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    //To make that class inflatable from XML we need to add constructor with Context and AttributeSet parameters.
    public FloatingActionButtonBehavior(Context context, AttributeSet attrs) {
    }

    /*Next step is to override method layoutDependsOn()
    and return true if we want to listen for changes. In our case we want to listen only for Snackbar objects changes.*/

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    //Method onDependentViewChanged is called each time depended view changes inside CoordinatorLayout.
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        child.setTranslationY(translationY);
        return true;
    }
}
