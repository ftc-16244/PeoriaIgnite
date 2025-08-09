package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DeadWheel {
    public DeadWheel(HardwareMap hwMap) {
        DcMotorImplEx parallel = hwMap.get(DcMotorImplEx.class, "parallel");
        DcMotorImplEx perpendicular = hwMap.get(DcMotorImplEx.class, "perpendicular");

        parallel.getCurrentPosition();
        perpendicular.getCurrentPosition();
    }
}
