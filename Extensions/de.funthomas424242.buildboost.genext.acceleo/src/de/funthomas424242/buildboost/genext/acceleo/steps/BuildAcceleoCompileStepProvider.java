package de.funthomas424242.buildboost.genext.acceleo.steps;

import java.io.File;
import java.util.Collections;
import java.util.List;

import de.devboost.buildboost.ant.AbstractAntTargetGeneratorProvider;
import de.devboost.buildboost.ant.IAntTargetGenerator;
import de.devboost.buildboost.genext.updatesite.artifacts.EclipseUpdateSiteDeploymentSpec;
import de.devboost.buildboost.model.IArtifact;
import de.devboost.buildboost.model.IBuildContext;

public class BuildAcceleoCompileStepProvider extends
		AbstractAntTargetGeneratorProvider {

	private final File targetDir;

	public BuildAcceleoCompileStepProvider(File targetDir) {
		super();
		this.targetDir = targetDir;
	}

	@Override
	public List<IAntTargetGenerator> getAntTargetGenerators(
			IBuildContext context, IArtifact artifact) {
		if (artifact instanceof EclipseUpdateSiteDeploymentSpec) {
			EclipseUpdateSiteDeploymentSpec updateSite = (EclipseUpdateSiteDeploymentSpec) artifact;
			IAntTargetGenerator step = new BuildAcceleoCompileStep(updateSite,
					targetDir);
			return Collections.singletonList(step);
		}
		return Collections.emptyList();
	}
}
