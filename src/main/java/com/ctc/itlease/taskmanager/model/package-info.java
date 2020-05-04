@GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter(name = "sequence_name", value = "TM_SEQUENCE"),
                @Parameter(name = "initial_value", value = "1000")}
)
package com.ctc.itlease.taskmanager.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;