function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //����һ������Ŀ��������������ʽ����
    var r = window.location.search.substr(1).match(reg); //ƥ��Ŀ�����
    if (r != null) return unescape(r[2]);
    return null; //���ز���ֵ
}