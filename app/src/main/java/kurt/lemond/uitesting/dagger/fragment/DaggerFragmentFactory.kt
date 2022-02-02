package kurt.lemond.uitesting.dagger.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject
import javax.inject.Provider

class DaggerFragmentFactory @Inject constructor(
    private val fragments: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val clazz = loadFragmentClass(classLoader, className)
        return instantiate(clazz) ?: super.instantiate(classLoader, className)
    }

    private fun instantiate(clazz: Class<out Fragment>): Fragment? =
        fragments[clazz]?.get()
}
