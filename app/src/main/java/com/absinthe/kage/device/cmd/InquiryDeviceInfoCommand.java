package com.absinthe.kage.device.cmd;

import com.absinthe.kage.connect.protocol.IpMessageConst;
import com.absinthe.kage.device.CommandBuilder;
import com.absinthe.kage.device.Device;

public class InquiryDeviceInfoCommand extends Device.Command {

    public String phoneName;

    public InquiryDeviceInfoCommand() {
        cmd = IpMessageConst.GET_DEVICE_INFO;
    }

    @Override
    public String pack() {
        return new CommandBuilder()
                .with(this)
                .append(phoneName)
                .build();
    }
}
