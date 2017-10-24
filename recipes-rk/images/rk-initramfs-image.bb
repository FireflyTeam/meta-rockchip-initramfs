# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Small image capable of booting a device. The kernel includes \
the Minimal RAM-based Initial Root Filesystem (initramfs), which finds the \
first 'init' program more efficiently."

LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES_remove += " splash"

CORE_IMAGE_EXTRA_INSTALL += " \
	init	\
	resources	\
"
