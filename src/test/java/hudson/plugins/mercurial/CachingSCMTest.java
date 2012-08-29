package hudson.plugins.mercurial;

import hudson.DescriptorExtensionList;
import hudson.model.Descriptor;
import hudson.model.Hudson;
import hudson.tools.ToolProperty;
import hudson.util.Iterators;

import java.util.Collections;

public class CachingSCMTest extends MercurialSCMTest {

    private static final String CACHING_INSTALLATION = "caching";

    private MercurialInstallation mi = new MercurialInstallation(CACHING_INSTALLATION, "",
            "hg", false, true, false, Collections.<ToolProperty<?>> emptyList());

	protected @Override void setUp() throws Exception {
        super.setUp();
        for (Descriptor d : Iterators.sequence(Hudson.getInstance().getExtensionList(Descriptor.class), DescriptorExtensionList.listLegacyInstances())) {
        	System.err.println(d.getId());
        }
        
        Hudson.getInstance()
                .getDescriptorByType(MercurialInstallation.DescriptorImpl.class)
                .setInstallations(mi);
        MercurialSCM.CACHE_LOCAL_REPOS = true;
    }

    @Override protected String hgInstallation() {
        return CACHING_INSTALLATION;
    }

}
