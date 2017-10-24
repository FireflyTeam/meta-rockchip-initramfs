#!/bin/sh

# Defaults
init="/sbin/init"
root="/dev/mmcblk2p7"

# Process command line options
get_opt() {
	echo "$@" | cut -d "=" -f 2
}

for i in $(cat /proc/cmdline); do
	case $i in
	root\=*)
		root=$(get_opt $i)
		;;
	init\=*)
		init=$(get_opt $i)
		;;
	esac
done

if [ $root = "PARTUUID" ]; then
	log "PARTUUID isn't support! Fall back to select a ext4 partition"
	root=$(findfs TYPE=ext4)
fi

mkdir -p /newroot
# Mount the root device
mount "${root}" /newroot

exec switch_root /newroot ${init}
