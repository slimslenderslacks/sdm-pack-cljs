import {
    ExtensionPack,
    metadata,
    SoftwareDeliveryMachine,
    CodeTransformRegistration,
} from "@atomist/sdm";
import * as cljs from "../../output";
import { ParameterType } from "@atomist/automation-client";

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
    transform: async (p, inv) => {
        await cljs.editLibraryVersion(p, inv.parameters.name, inv.parameters.version);
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