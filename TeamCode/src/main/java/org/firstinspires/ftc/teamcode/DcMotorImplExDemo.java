package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

public class DcMotorImplExDemo {
    DcMotorImplEx motor;

    public DcMotorImplExDemo(HardwareMap hwMap) {
        motor = hwMap.get(DcMotorImplEx.class, "motor");

        // 10, 0, 0, 0 is default
        PIDFCoefficients pidCoeffs = new PIDFCoefficients(10, 0, 0, 0);
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidCoeffs);
    }
}
