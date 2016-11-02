#coding=utf-8

import random


def getUserAgent():
    """
    生成虚假user-agent程序,目前有450个虚假user-agent
    :return:
    """
    company = "Mozilla/5.0"
    platform = "linux"
    encryption = "U"
    os_lan_device_set = ["Android 5.0.2; zh-cn; PLK-TLO1H Build/HDPLK-TL01H",
                         "Android 5.1.0; zh-cn; ATH-TL00H Build/HDATH-TL00H",
                         "Android 5.0.2; zh-cn; GEM-703L Build/HDGEM-703L",
                         "Android 4.4.2; zh-cn; PE-TL20 Build/HDPE-TL20",
                         "Android 5.1.2; zh-ch; KIW-TL00H Build/HDKIW-TL00H",
                         "Android 4.4.2; zh-ch; CHM-UL00 Build/HDCHM-UL00",
                         "Android 5.1.2; zh-ch; KIW-AL10 Build/HDKIW-AL10",
                         "Android 5.0.2; zh-ch; PLK-AL10 Build/HDPLK-AL10",
                         "Android 5.1.2; zh-ch; TAG-AL00 Build/HDTAG-AL00",
                         "Android 4.4.2; zh-ch; HUAWEI MT7-CL00/HuaweiMT7-CL00",
                         "Android 6.0.2; zh-ch; HUAWEI NXT-AL10/HuaweiNXT-AL10",
                         "Android 5.1.2; zh-ch; TIT-AL00 Build/HDTIT-AL00",
                         "Android 5.1.2; zh-ch; G7 Plus Build/HDG7 Plus",
                         "Android 5.1.2; zh-ch; HUAWEI CRR-UL00/HuaweiCRR-UL00",
                         "Android 5.0.2; zh-ch; HUAWEI ALE-TL00/HuaweiALE-TL00",
                         "Android 4.2.2; zh-ch; H30-T10/HDH30-T10",
                         "Android 5.0; zh-ch; NX511J Build/LRX22G",
                         "Android 5.0; zh-ch; NX512J Build/LRX22G",
                         "Android 5.0; zh-ch; NX508J Build/LRX22G",
                         "Android 5.0; zh-ch; NX513J Build/LRX22G",
                         "Android 4.3; zh-cn; HM 1SC Build/JLS36C",
                         "Android 4.1.2; zh-ch; MI 1 Build/JZO54K",
                         "Android 4.1.2; zh-ch; MI 1S Build/JZO54K",
                         "Android 4.3; zh-ch; MI 2 Build/JZO54K",
                         "Android 4.3; zh-ch; MI 2S Build/JZO54K"]
    os_lan_device = os_lan_device_set[random.randint(0,len(os_lan_device_set)-1)]

    rendering_version_set = ["534.30","537.36"]
    rendering_version = rendering_version_set[random.randint(0,len(rendering_version_set)-1)]
    rendering_engine = "AppleWebKit/%s (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0" % rendering_version
    extensions_set = ["Mobile Safari/%s" % rendering_version,
                      "Mobile Safari/%s LieBaoFast/3.25.7" % rendering_version,
                      "UCBrowser/10.9.1.711 U3/0.8.0 Mobile Safari/%s" % rendering_version,
                      "Mobile Safari/%s baidubrowser/6.3.20.0 (Baidu; P1 4.4.2)" % rendering_version,
                      "MQQBrowser/6.3 Mobile Safari/%s" % rendering_version,
                      "Mobile Safari/%s SogouMSE,SogouMobileBrowser/4.1.4" % rendering_version,
                      "OPR/11.1.0.99116 Mobile Safari/%s" % rendering_version,
                      "Mobile Safari/%s DolphinBrowserCN/11.2.6.170" % rendering_version,
                      "Mobile Safari/%s Mb2345Browser/7.5.3" % rendering_version]
    extensions = extensions_set[random.randint(0,len(extensions_set)-1)]
    userAgent = company+"("+platform+"; "+encryption+"; "+os_lan_device+") "+rendering_engine+" "+ extensions
    return userAgent