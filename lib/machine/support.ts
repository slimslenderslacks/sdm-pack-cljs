import {
    ExtensionPack,
    metadata,
    SoftwareDeliveryMachine,
    CodeTransformRegistration,
} from "@atomist/sdm";
import * as cljs from "../../output";
import { ParameterType, editModes, logger } from "@atomist/automation-client";

interface LeinDepVersionParameters extends ParameterType {
    name: string,
    version: string,
}

const AddPlugin: CodeTransformRegistration<LeinDepVersionParameters> = {
    name: "AddLeiningenPlugin",
    intent: "cljs",
    parameters: {
        name: { required: true },
        version: { required: true }
    },
    autoSubmit: true,
    transformPresentation: (inv, p) => {
        return new editModes.PullRequest(
            "master",
            "Edit an sexpr!"
        );
    },
    transform: async (p, inv) => {
        const val: boolean = await cljs.editLibraryVersion(p, inv.parameters.name, inv.parameters.version);
        logger.info(`complete:  ${val}`);
        return p;
    },
}

export function cljsSupport(): ExtensionPack {
    return {
        ...metadata(),
        configure: (sdm: SoftwareDeliveryMachine) => {
            sdm.addCodeTransformCommand(AddPlugin);
        },
    };
}