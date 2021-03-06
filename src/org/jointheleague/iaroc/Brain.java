package org.jointheleague.iaroc;

import android.os.SystemClock;

import ioio.lib.api.IOIO;
import ioio.lib.api.exception.ConnectionLostException;

import org.jointheleague.erik.irobot.IRobotCreateAdapter;
import org.jointheleague.erik.irobot.IRobotCreateInterface;
import org.jointheleague.iaroc.sensors.UltraSonicSensors;

public class Brain extends IRobotCreateAdapter {
    private final Dashboard dashboard;
    public UltraSonicSensors sonar;

    public Brain(IOIO ioio, IRobotCreateInterface create, Dashboard dashboard)
            throws ConnectionLostException {
        super(create);
        sonar = new UltraSonicSensors(ioio, dashboard);
        this.dashboard = dashboard;
    }

    /* This method is executed when the robot first starts up. */
    public void initialize() throws ConnectionLostException {
        dashboard.log("Hello! I'm a Clever Robot!");
        //what would you like me to do, Clever Human?


    }

    /* This method is called repeatedly. */
    public void loop() throws ConnectionLostException, InterruptedException {
        sonar.read();
        dashboard.log(sonar.getRightDistance() + "   " + sonar.getFrontDistance() + "   " + sonar.getLeftDistance());
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        driveDirect(500,500);
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        if (isBumpLeft()) {
            driveDirect(-350, -350);
            SystemClock.sleep(800);
            driveDirect(200, 0);
            SystemClock.sleep(1000);

        }

        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        if (isBumpRight()) {
            driveDirect(-350, -350);
            SystemClock.sleep(750);
            driveDirect(0, 340);
            SystemClock.sleep(1000);
        }
        readSensors(SENSORS_BUMPS_AND_WHEEL_DROPS);
        if (isBumpRight() && isBumpLeft()) {

            driveDirect(300, 300);
            SystemClock.sleep(1000);
            driveDirect(0, 300);
            SystemClock.sleep(1000);

        }


    }


}