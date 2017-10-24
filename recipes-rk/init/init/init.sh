#!/bin/sh

log() {
	echo "Init: $1"
	echo "Init: $1" > /dev/tty1
}

# Mount things needed by this script
/bin/mount -t proc proc /proc
/bin/mount -t sysfs sysfs /sys
/bin/mount -t tmpfs tmpfs /run
/bin/mount -t devtmpfs devtmpfs /dev
# Set up device nodes
mdev -s

# Whether it should boot into internal rootfs
source /rk/stop.sh

# Boot into rootfs
source /rk/boot.sh

# This will only be run if the exec above failed
log "Failed to switch_root, dropping to the shell"
exec sh
