import { h } from 'vue';
import { Icon } from '@iconify/vue';

export const rendreIcon = (icon: string) => {
  return () =>
    h(Icon, {
      icon: icon,
    });
};
export function isMobile() {
  if (
    navigator.userAgent.match(
      /(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i
    )
  ) {
    return true; // 手机端
  } else {
    return false; // alert('PC端')
  }
}
