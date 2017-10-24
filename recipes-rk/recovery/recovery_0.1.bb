# Copyright (C) 2017 Fuzhou Rockchip Electronics Co., Ltd
# Released under the MIT license (see COPYING.MIT for the terms)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
	file://init.sh \
"
S = "${WORKDIR}"

inherit allarch

do_install () {
	install -d ${D}/rk/
	install -m 0755 ${WORKDIR}/*.sh ${D}/rk/
}

FILES_${PN} = "/rk/*"

RDEPENDS_${PN} += "bash"

