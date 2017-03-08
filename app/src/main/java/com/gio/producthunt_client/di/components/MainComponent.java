package com.gio.producthunt_client.di.components;

import dagger.Component;
import ru.itlc.android.ubirasha.di.ActivityScope;
import ru.itlc.android.ubirasha.di.modules.MainModule;
import ru.itlc.android.ubirasha.ui.main.MainActivity;
import ru.itlc.android.ubirasha.ui.main.container.ContainerFragment;
import ru.itlc.android.ubirasha.ui.main.deposit.DepositFragment;
import ru.itlc.android.ubirasha.ui.main.feedback.FeedbackFragment;
import ru.itlc.android.ubirasha.ui.main.tabhistory.HistoryFragment;
import ru.itlc.android.ubirasha.ui.main.tabnew.NewFragment;
import ru.itlc.android.ubirasha.ui.main.tabwait.WaitFragment;

/**
 * Created by Alexey Mitutov on 20.12.2016.
 * ИТЛ Консалтинг
 */

@ActivityScope
@Component(
        dependencies = UbirashaAppComponent.class,
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity mainActivity);
    void inject(ContainerFragment containerFragment);
    void inject(NewFragment newFragment);
    void inject(HistoryFragment historyFragment);
    void inject(WaitFragment waitFragment);
    void inject(DepositFragment depositFragment);
    void inject(FeedbackFragment feedbackFragment);
}
