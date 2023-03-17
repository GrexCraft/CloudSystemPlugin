package net.grexcraft.cloud_plugin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.grexcraft.cloud.core.enums.ServerState;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyServerRequest {
    String serverName;
    ServerState state;
}
