import {
    ExtensionPack,
    metadata,
    SoftwareDeliveryMachine,
    CodeTransformRegistration,
} from "@atomist/sdm";
import * as cljs from "../../output";

const AddPlugin: CodeTransformRegistration = {
    name: "AddLeiningenPlugin",
    intent: "cljs",
    transform: async (p) => {
        await cljs.addPlugin(p);
        return p;
    }
}

export function cljsSupport(): ExtensionPack {
    return {
        ...metadata(),
        configure: (sdm: SoftwareDeliveryMachine) => {
            sdm.addCodeTransformCommand(AddPlugin);
        },
    };
}