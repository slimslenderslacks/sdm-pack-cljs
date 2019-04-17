import { Configuration } from "@atomist/automation-client";
import { SoftwareDeliveryMachineConfiguration, SoftwareDeliveryMachine } from "@atomist/sdm";
import { cljsSupport } from "../lib/machine/support";
import {
    configureSdm,
    createSoftwareDeliveryMachine,
} from "@atomist/sdm-core";

export function machineMaker(config: SoftwareDeliveryMachineConfiguration): SoftwareDeliveryMachine {

    const sdm = createSoftwareDeliveryMachine(
        {
            name: `${configuration.name}-test`,
            configuration: config,
        }
    );
    sdm.addExtensionPacks(
        cljsSupport(),
    )

    return sdm;
}

export const configuration: Configuration = {
    postProcessors: [
        configureSdm(machineMaker),
    ],
};
