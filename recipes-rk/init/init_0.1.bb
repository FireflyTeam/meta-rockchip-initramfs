# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

SUMMARY = "Rockchip Initramfs Scripts/Tools"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
	file://boot.sh \
	file://init.sh \
	file://stop.sh \
"
S = "${WORKDIR}"

inherit allarch

do_install () {
	install -d ${D}/rk
	install -m 0755 ${WORKDIR}/init.sh ${D}/init
	install -m 0755 ${WORKDIR}/boot.sh ${D}/rk/boot.sh
	install -m 0755 ${WORKDIR}/stop.sh ${D}/rk/stop.sh
}

FILES_${PN} = "/init /rk/*"

RDEPENDS_${PN} += "util-linux-findfs fbida"
