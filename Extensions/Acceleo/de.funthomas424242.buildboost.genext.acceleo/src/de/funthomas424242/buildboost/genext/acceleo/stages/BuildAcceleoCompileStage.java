package de.funthomas424242.buildboost.genext.acceleo.stages;

import java.io.File;

import de.devboost.buildboost.AutoBuilder;
import de.devboost.buildboost.BuildContext;
import de.devboost.buildboost.BuildException;
import de.devboost.buildboost.ant.AntScript;
import de.devboost.buildboost.discovery.EclipseFeatureFinder;
import de.devboost.buildboost.discovery.EclipseTargetPlatformAnalyzer;
import de.devboost.buildboost.discovery.PluginFinder;
import de.devboost.buildboost.genext.updatesite.discovery.EclipseUpdateSiteDeploymentSpecFinder;
import de.devboost.buildboost.genext.updatesite.discovery.EclipseUpdateSiteFinder;
import de.devboost.buildboost.model.IUniversalBuildStage;
import de.devboost.buildboost.stages.AbstractBuildStage;
import de.funthomas424242.buildboost.genext.acceleo.steps.BuildAcceleoCompileStepProvider;

public class BuildAcceleoCompileStage extends AbstractBuildStage implements
		IUniversalBuildStage {

	private String artifactsFolder;

	public void setArtifactsFolder(String artifactsFolder) {
		this.artifactsFolder = artifactsFolder;
	}

	@Override
	public AntScript getScript() throws BuildException {
		File buildDir = new File(artifactsFolder);

		BuildContext context = createContext(false);

		context.addBuildParticipant(new EclipseTargetPlatformAnalyzer(buildDir));

		context.addBuildParticipant(new PluginFinder(buildDir));
		context.addBuildParticipant(new EclipseFeatureFinder(buildDir));
		context.addBuildParticipant(new EclipseUpdateSiteFinder(buildDir));
		context.addBuildParticipant(new EclipseUpdateSiteDeploymentSpecFinder(
				buildDir));

		context.addBuildParticipant(new BuildAcceleoCompileStepProvider(
				buildDir));

		AutoBuilder builder = new AutoBuilder(context);

		AntScript script = new AntScript();
		script.setName("Build Acceleo");
		script.addTargets(builder.generateAntTargets());

		return script;
	}

	@Override
	public int getPriority() {
		return 10100;
	}
}
