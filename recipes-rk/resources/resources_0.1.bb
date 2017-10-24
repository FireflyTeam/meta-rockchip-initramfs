# Copyright (C) 2016 - 2017 Jacob Chen <jacob2.chen@rock-chips.com>
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
	file://rockchip_log.png \
"
S = "${WORKDIR}"

inherit allarch

do_install () {
	install -d ${D}/rk
	install -m 0755 ${WORKDIR}/rockchip_log.png ${D}/rk/logo.png
}

FILES_${PN} = "/rk/*"
