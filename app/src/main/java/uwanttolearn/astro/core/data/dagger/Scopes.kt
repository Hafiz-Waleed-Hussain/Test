package uwanttolearn.astro.core.data.dagger

import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * Created by waleed on 24/07/2017.
 */
@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScopeWithNewCreation

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class IntentServiceScope

@Scope
@Retention(AnnotationRetention.BINARY)
annotation class FragmentScope
