# Introduction
This repo contain a yocto layer, a set of init/recovery scripts.
It will create a initramfs image, which could be used to support many fucntions, 
like battery monitor, recovery, multi-os boot.It's also possible to use it to create a sd-card recovery image.

Please note that it's not a complete system solution, it's rough.
It's just offered to give you a thought. 



# Usage

Download rockchip yocto/oe environment.

http://opensource.rock-chips.com/wiki_Yocto

Configure with `rk-minimal` distro.

    DISTRO=rk-minimal . ./setup-environment -b initramfs

Add `meta-rockchip-initramfs` to `conf/bblayers.conf`.

    ${BSPDIR}/sources/meta-rockchip-initramfs

Build initramfs image.

    bitbake rk-initramfs-image

Copy image to the boot partiton.

    cp initramfs/tmp/deploy/images/<MACHINE>/rk-initramfs-image.cpio.gz /media/user/boot

Set `initrd` in `extlinux.conf`.
(Note: PARTUUID is not support in this initramfs because it need udev work. It will randomly select a ext4 partition if PARTUUID is used)

    default yocto

    label yocto
        kernel /zImage
        devicetree /rk3288-evb-act8846.dtb
        initrd /rk-initramfs-image-evb-rk3288.cpio.gz
        append console=ttyS2,115200n8 rw root=PARTUUID=69dad710-2c rootfstype=ext4 init=/sbin/init

# Customize

### Add Stop Condition

`recipes-rk/init/init/stop.sh`


### Change boot media

`recipes-rk/init/init/boot.sh`

### Recovery

The recovery scripts is in `recipes-rk/recovery/*`.

# Others


## Plan
* Init
    * low battery check

* Recovery
    * upgrade from network/usb/sd-card
    * reboot to ums/rockusb/fastboot


##  Scenarios

```
+-------------------------------+
|                               |
|          eMMC U-boot          |
|                               |
+--------------+----------------+            +-----------+-------------+
               |        sd-card recovery     |     Kernel,Initramfs    |
               | ---------------------------->          in             |
               |                             |        SD-Card          | 
+--------------v----------------+            +-----------+-------------+ 
|                               |
|            Kernel             |
|                               |
+--------------+----------------+ 
               |
               |
+--------------|----------------+
|              |                |
|              |                |
|  +-----------v-------------+  |
|  |                         |  |
|  |       Initfamfs         |  |
|  |                         |  |
|  +-----------+-------------+  |
|              |                |
|              |                |
|  +-----------v-------------+  |
|  |                         |  | 
|  |         Rootfs          |  |
|  |                         |  | 
|  +-----------+-------------+  |
+-------------------------------+
```

##  Screenshot

### Init

![](https://i.imgur.com/vGeXfMh.png)

### Recovery
